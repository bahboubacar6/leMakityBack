package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.AppOrderDTO;
import com.group.makity.leMakity.dtos.OrderHistoryDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.OrderNotFoundException;

import java.util.List;

public interface AppOrderService {

    AppOrderDTO findById(Long idOrder) throws OrderNotFoundException;
    AppOrderDTO saveOrder(AppOrderDTO appOrderDTO) throws AppUserNotFoundException;
    AppOrderDTO updateOrder(AppOrderDTO appOrderDTO) throws OrderNotFoundException;
    List<AppOrderDTO> listOrders();
    boolean deleteOrderById(Long idOrder);
    OrderHistoryDTO listPageOrder(int page, int size);
}
