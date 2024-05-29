package org.k_lab.catchku.service;

import lombok.RequiredArgsConstructor;
import org.k_lab.catchku.controller.dto.request.item.ItemCreateRequest;
import org.k_lab.catchku.controller.dto.request.item.ItemDeleteRequest;
import org.k_lab.catchku.controller.dto.response.item.ItemInfoResponse;
import org.k_lab.catchku.domain.Item;
import org.k_lab.catchku.domain.UserItem;
import org.k_lab.catchku.exception.ErrorStatus;
import org.k_lab.catchku.exception.model.ConflictException;
import org.k_lab.catchku.exception.model.NotFoundException;
import org.k_lab.catchku.infrastructure.ItemRepository;
import org.k_lab.catchku.infrastructure.UserItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;

    @Transactional
    public ItemInfoResponse create(ItemCreateRequest request) {
        if (itemRepository.existsByName(request.itemName()))
            throw new ConflictException(ErrorStatus.ALREADY_EXIST_ITEM_EXCEPTION, ErrorStatus.ALREADY_EXIST_ITEM_EXCEPTION.getMessage());
        Item item = Item.newInstance(request.itemName(), request.itemValue());
        itemRepository.save(item);
        return new ItemInfoResponse(item.getName(), item.getValue());
    }

    @Transactional
    public void delete(ItemDeleteRequest request) {
        Item item = itemRepository.findByName(request.itemName());
        if (item == null)
            throw new NotFoundException(ErrorStatus.NOT_FOUND_ITEM_EXCEPTION, ErrorStatus.NOT_FOUND_ITEM_EXCEPTION.getMessage());
        for (UserItem userItem : item.getUserItemList()) {
            userItem.getUser().getItemList().remove(userItem);
            userItemRepository.delete(userItem);
        }
        itemRepository.delete(item);
    }
}
