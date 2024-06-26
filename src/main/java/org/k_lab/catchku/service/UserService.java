package org.k_lab.catchku.service;

import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.controller.dto.request.user.*;
import org.k_lab.catchku.controller.dto.response.user.UserCatchedKuResponse;
import org.k_lab.catchku.controller.dto.response.user.UserLoginResponse;
import org.k_lab.catchku.controller.dto.response.user.UserOwnedItemResponse;
import org.k_lab.catchku.controller.dto.response.user.UserRegisterResponse;
import org.k_lab.catchku.domain.*;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.model.ConflictException;
import org.k_lab.catchku.exception.model.NotFoundException;
import org.k_lab.catchku.infrastructure.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final KuRepository kuRepository;
    private final UserKuRepository userKuRepository;
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new ConflictException(ErrorStatus.ALREADY_EXIST_USER_EXCEPTION, ErrorStatus.ALREADY_EXIST_USER_EXCEPTION.getMessage());
        // 학과가 존재하면 그거 쓰고, 없으면 만들거임
        Department department = departmentRepository.findByDepartmentName(request.departmentName());
        if (department == null) {
            department = Department.newInstance(request.departmentName());
            departmentRepository.save(department);
        }
        User newUser = User.newInstance(request.email(), request.password(), request.name(), department);
        userRepository.save(newUser);
        return new UserRegisterResponse(newUser.getId(), newUser.getEmail(), newUser.getName());
    }

    @Transactional
    public void delete(UserInfoRequest request) {
        User user = checkUserExist(request.userId());
        for (UserKu userKu : user.getKuList()) {
            userKu.getKu().getUserKuList().remove(userKu);
            userKuRepository.delete(userKu);
        }
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest request) {
        // 해당 이메일로 가입한 유저가 없음
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage()));
        // 이메일은 존재하지만 비밀번호가 다름
        // + 이후 암호화 try
        if (!user.getPassword().equals(request.password()))
            throw new NotFoundException(ErrorStatus.REQUEST_PASSWORD_EXCEPTION, ErrorStatus.REQUEST_PASSWORD_EXCEPTION.getMessage());
        return new UserLoginResponse(user.getId(), user.getEmail(), user.getName(), user.getDepartment().getDepartmentName());
    }

    // 잡은 Ku 최근순으로 return
    @Transactional(readOnly = true)
    public List<UserCatchedKuResponse> getKuList(Long userId) {
        checkUserExist(userId);
        List<UserKu> kuList = userKuRepository.findByUserIdOrderByCreatedDateDesc(userId);
        List<UserCatchedKuResponse> response = new ArrayList<>();
        for (UserKu userKu : kuList) {
            response.add(new UserCatchedKuResponse(userKu.getKu().getName(), userKu.getCreatedDate()));
        }
        return response;
    }

    @Transactional
    public void obtainItem(UserItemInteractRequest request) {
        User user = checkUserExist(request.userId());
        Item item = checkItemExist(request.itemName());
        UserItem userItem = UserItem.newInstance(user, item);
        userItemRepository.save(userItem);
    }

    @Transactional
    public void useItem(UserItemInteractRequest request) {
        User user = checkUserExist(request.userId());
        checkItemExist(request.itemName());
        for (UserItem userItem : user.getItemList()) {
            if (request.itemName().equals(userItem.getItem().getName())) {
                user.getItemList().remove(userItem);
                userItemRepository.delete(userItem);
                return;
            }
        }
        throw new NotFoundException(ErrorStatus.NOT_FOUND_USER_ITEM_EXCEPTION, ErrorStatus.NOT_FOUND_USER_ITEM_EXCEPTION.getMessage());
    }

    // 보유한 Items 오름차순 return
    @Transactional(readOnly = true)
    public List<UserOwnedItemResponse> getItemList(Long userId) {
        checkUserExist(userId);
        return userItemRepository.findItemsByUserId(userId);
    }

    @Transactional
    public void catchKu(UserCatchKuRequest request) {
        User user = checkUserExist(request.userId());
        Ku ku = checkKuExist(request.kuName());
        UserKu userKu = UserKu.newInstance(user, ku);
        userKuRepository.save(userKu);
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

    private Item checkItemExist(String itemName) {
        Item item = itemRepository.findByName(itemName);
        if (item == null)
            throw new NotFoundException(ErrorStatus.NOT_FOUND_ITEM_EXCEPTION, ErrorStatus.NOT_FOUND_ITEM_EXCEPTION.getMessage());
        return item;
    }
}
