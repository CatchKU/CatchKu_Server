package org.k_lab.catchku.service;

import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.controller.dto.request.ku.KuRequest;
import org.k_lab.catchku.controller.dto.response.ku.KuResponse;
import org.k_lab.catchku.domain.Ku;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.model.ConflictException;
import org.k_lab.catchku.infrastructure.KuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KuService {
    private final KuRepository kuRepository;

    @Transactional
    public KuResponse create(KuRequest request) {
        if (kuRepository.existsByName(request.kuName()))
            throw new ConflictException(ErrorStatus.ALREADY_EXIST_KU_EXCEPTION, ErrorStatus.ALREADY_EXIST_KU_EXCEPTION.getMessage());
        Ku ku = Ku.newInstance(request.kuName(), request.score());
        kuRepository.save(ku);
        return new KuResponse(ku.getName(), ku.getScore());
    }
}
