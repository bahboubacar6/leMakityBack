package com.group.makity.leMakity.web;

import com.group.makity.leMakity.dtos.AppOrderDTO;
import com.group.makity.leMakity.dtos.OrderHistoryDTO;
import com.group.makity.leMakity.dtos.UserHistoryDTO;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.exceptions.OrderNotFoundException;
import com.group.makity.leMakity.services.AppOrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
public class AppOrderController {

    private AppOrderService appOrderService;


    public AppOrderController(AppOrderService appOrderService) {
        this.appOrderService = appOrderService;
    }

    @GetMapping("/all")
    public List<AppOrderDTO> listOrders(){
        return appOrderService.listOrders();
    }

    @GetMapping("/pageOrder")
    public OrderHistoryDTO listOrderPage(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "3") int size){
        return appOrderService.listPageOrder(page, size);
    }

    @GetMapping("/{idOrder}")
    public AppOrderDTO getOrder(@PathVariable Long idOrder) throws OrderNotFoundException {
        return appOrderService.findById(idOrder);
    }

    @PostMapping("/save")
    public AppOrderDTO saveOrder(@RequestBody AppOrderDTO appOrderDTO) throws AppUserNotFoundException {
        return appOrderService.saveOrder(appOrderDTO);
    }

    @PutMapping("/update/{id}")
    public AppOrderDTO updateOrder(@PathVariable(name = "id") Long idOrder, @RequestBody AppOrderDTO appOrderDTO) throws OrderNotFoundException {
        appOrderDTO.setIdOrder(idOrder);
        return appOrderService.updateOrder(appOrderDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(Long id){
        return appOrderService.deleteOrderById(id);
    }
}
