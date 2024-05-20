package org.k_lab.catchku.service;

import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.controller.dto.request.ku.KuCreateRequest;
import org.k_lab.catchku.controller.dto.request.ku.KuDeleteRequest;
import org.k_lab.catchku.controller.dto.response.ku.KuCountByDepartmentResponse;
import org.k_lab.catchku.controller.dto.response.ku.KuCountByUserResponse;
import org.k_lab.catchku.controller.dto.response.ku.KuInfoResponse;
import org.k_lab.catchku.domain.Ku;
import org.k_lab.catchku.domain.UserKu;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.model.ConflictException;
import org.k_lab.catchku.exception.model.NotFoundException;
import org.k_lab.catchku.infrastructure.KuRepository;
import org.k_lab.catchku.infrastructure.UserKuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KuService {
    private final KuRepository kuRepository;
    private final UserKuRepository userKuRepository;

    @Transactional
    public KuInfoResponse create(KuCreateRequest request) {
        if (kuRepository.existsByName(request.kuName()))
            throw new ConflictException(ErrorStatus.ALREADY_EXIST_KU_EXCEPTION, ErrorStatus.ALREADY_EXIST_KU_EXCEPTION.getMessage());
        Ku ku = Ku.newInstance(request.kuName(), request.score());
        kuRepository.save(ku);
        return new KuInfoResponse(ku.getName(), ku.getScore());
    }

    @Transactional
    public void delete(KuDeleteRequest request) {
        Ku ku = kuRepository.findByName(request.kuName());
        if (ku == null)
            throw new NotFoundException(ErrorStatus.NOT_FOUND_KU_EXCEPTION, ErrorStatus.NOT_FOUND_KU_EXCEPTION.getMessage());
        for (UserKu userKu : ku.getUserList()) {
            userKu.getUser().getKuList().remove(userKu);
            userKuRepository.delete(userKu);
        }
        kuRepository.delete(ku);
    }

    @Transactional(readOnly = true)
    public List<KuCountByDepartmentResponse> getTop5DepartmentsByKuCount() {
        return userKuRepository.findTop5DepartmentByKuCount();
    }

    @Transactional(readOnly = true)
    public List<KuCountByUserResponse> getTop5UsersByKuCount() {
        return userKuRepository.findTop5UsersByKuCount();
    }
}
