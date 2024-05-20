package org.k_lab.catchku.service;

import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.controller.dto.request.user.UserCatchKuRequest;
import org.k_lab.catchku.controller.dto.request.user.UserLoginRequestDto;
import org.k_lab.catchku.controller.dto.request.user.UserRegisterRequestDto;
import org.k_lab.catchku.controller.dto.response.UserKuListResponse;
import org.k_lab.catchku.controller.dto.response.UserLoginResponseDto;
import org.k_lab.catchku.controller.dto.response.UserRegisterResponseDto;
import org.k_lab.catchku.domain.Department;
import org.k_lab.catchku.domain.Ku;
import org.k_lab.catchku.domain.User;
import org.k_lab.catchku.domain.UserKu;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.model.ConflictException;
import org.k_lab.catchku.exception.model.NotFoundException;
import org.k_lab.catchku.infrastructure.DepartmentRepository;
import org.k_lab.catchku.infrastructure.KuRepository;
import org.k_lab.catchku.infrastructure.UserKuRepository;
import org.k_lab.catchku.infrastructure.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final KuRepository kuRepository;
    private final UserKuRepository userKuRepository;

    @Transactional
    public UserRegisterResponseDto signup(UserRegisterRequestDto request) {
        if (userRepository.existsByEmail(request.email()))
            throw new ConflictException(ErrorStatus.ALREADY_EXIST_USER_EXCEPTION, ErrorStatus.ALREADY_EXIST_USER_EXCEPTION.getMessage());
        // 학과가 존재하면 그거 쓰고, 없으면 만들거임
        Optional<Department> department = departmentRepository.findByDepartmentName(request.departmentName());
        Department newDepartment;
        if (department.isEmpty()) {
            newDepartment = Department.newInstance(request.departmentName());
            departmentRepository.save(newDepartment);
        } else {
            newDepartment = department.get();
        }
        User newUser = User.newInstance(request.email(), request.password(), request.name(), newDepartment);
        userRepository.save(newUser);
        return new UserRegisterResponseDto(newUser.getId(), newUser.getEmail(), newUser.getName());
    }

    public UserLoginResponseDto login(UserLoginRequestDto request) {
        // 해당 이메일로 가입한 유저가 없음
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage()));
        // 이메일은 존재하지만 비밀번호가 다름
        // + 이후 암호화 try
        if (!user.getPassword().equals(request.password()))
            throw new NotFoundException(ErrorStatus.REQUEST_PASSWORD_EXCEPTION, ErrorStatus.REQUEST_PASSWORD_EXCEPTION.getMessage());
        return new UserLoginResponseDto(user.getId(), user.getEmail(), user.getName(), user.getDepartment().getDepartmentName());
    }

    public UserKuListResponse getKuList(Long userId) {
//        User user = checkUserExist(userId);
//        return new UserKuListResponse(user.getKuList());
        return null;
    }

    @Transactional
    public Boolean catchKu(UserCatchKuRequest request) {
        User user = checkUserExist(request.userId());
        Ku ku = checkKuExist(request.kuName());
        UserKu userKu = UserKu.newInstance(user, ku);
        userKuRepository.save(userKu);
        return true;
    }

    private User checkUserExist(Long userId) {
        User user = userRepository.findById(userId);
        if (user == null)
            throw new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage());
        return user;
    }

    private Ku checkKuExist(String kuName) {
        Ku ku = kuRepository.findByName(kuName);
        if (ku == null)
            throw new NotFoundException(ErrorStatus.NOT_FOUND_KU_EXCEPTION, ErrorStatus.NOT_FOUND_KU_EXCEPTION.getMessage());
        return ku;
    }
}
