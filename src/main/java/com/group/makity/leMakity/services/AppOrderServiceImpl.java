package com.group.makity.leMakity.services;

import com.group.makity.leMakity.dtos.AppOrderDTO;
import com.group.makity.leMakity.entities.AppOrder;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.OrderNotFoundException;
import com.group.makity.leMakity.mappers.AppOrderMapImpl;
import com.group.makity.leMakity.mappers.AppOrderMapper;
import com.group.makity.leMakity.repositories.AppOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AppOrderServiceImpl implements AppOrderService {

    private static final String ORDER_NOT_FOUND = "La commande n'existe pas";
    private AppOrderRepository appOrderRepository;
    private AppOrderMapper appOrderMapper;
    private AppOrderMapImpl appOrderMap;

    public AppOrderServiceImpl(AppOrderRepository appOrderRepository, AppOrderMapper appOrderMapper, AppOrderMapImpl appOrderMap) {
        this.appOrderRepository = appOrderRepository;
        this.appOrderMapper = appOrderMapper;
        this.appOrderMap = appOrderMap;
    }

    @Override
    public AppOrderDTO findById(Long idOrder) throws OrderNotFoundException {
        AppOrder appOrder = appOrderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND));
        AppOrderDTO appOrderDTO = appOrderMapper.toDto(appOrder);
        return appOrderDTO;
    }

    /*@Override
    public AppOrderDTO saveOrder(AppOrderDTO appOrderDTO) {
        AppOrder appOrder = appOrderMapper.toEntity(appOrderDTO);
        AppOrder appOrderSaved = appOrderRepository.save(appOrder);
        AppOrderDTO appOrderDTOSaved = appOrderMapper.toDto(appOrderSaved);
        return appOrderDTOSaved;
    }*/

    @Override
    public AppOrderDTO saveOrder(AppOrderDTO appOrderDTO) throws AppUserNotFoundException {
        AppOrder appOrder = appOrderMap.toEntity(appOrderDTO);
        AppOrder appOrderSaved = appOrderRepository.save(appOrder);
        AppOrderDTO appOrderDTOSaved = appOrderMap.toDto(appOrderSaved);
        return appOrderDTOSaved;
    }

    @Override
    public AppOrderDTO updateOrder(AppOrderDTO appOrderDTO) throws OrderNotFoundException {
        AppOrder appOrder = appOrderRepository.findById(appOrderDTO.getIdOrder()).get();
        if (appOrder == null){
            throw new OrderNotFoundException(ORDER_NOT_FOUND);
        }

        if (!Objects.equals(appOrder.getDate(), appOrderDTO.getDate())){
            appOrder.setDate(appOrderDTO.getDate());
        }
        AppOrder appOrderSaved = appOrderRepository.save(appOrder);
        AppOrderDTO appOrderDTOSaved = appOrderMapper.toDto(appOrderSaved);
        return appOrderDTOSaved;
    }

    @Override
    public List<AppOrderDTO> listOrders() {
        List<AppOrder> orders = appOrderRepository.findAll();
        List<AppOrderDTO> orderDTOS = orders.stream().map(cmd -> appOrderMapper.toDto(cmd)).collect(Collectors.toList());
        return orderDTOS;
    }

    @Override
    public boolean deleteOrderById(Long idOrder) {
        appOrderRepository.deleteById(idOrder);
        return true;
    }
}
