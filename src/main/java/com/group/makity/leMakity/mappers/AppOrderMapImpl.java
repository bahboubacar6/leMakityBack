package com.group.makity.leMakity.mappers;

import com.group.makity.leMakity.dtos.AppOrderDTO;
import com.group.makity.leMakity.dtos.ProductDTO;
import com.group.makity.leMakity.entities.AppOrder;
import com.group.makity.leMakity.entities.AppUser;
import com.group.makity.leMakity.entities.Product;
import com.group.makity.leMakity.exceptions.AppUserNotFoundException;
import com.group.makity.leMakity.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppOrderMapImpl {

    private static final String USER_NOT_FOUND = "L'utilisateur n'existe pas";
    private ProductMapper productMapper;
    private AppUserRepository appUserRepository;

    public AppOrderMapImpl(ProductMapper productMapper, AppUserRepository appUserRepository) {
        this.productMapper = productMapper;
        this.appUserRepository = appUserRepository;
    }

    public AppOrder toEntity(AppOrderDTO appOrderDTO) throws AppUserNotFoundException {
        if ( appOrderDTO == null ) {
            return null;
        }

        AppOrder appOrder = new AppOrder();
        AppUser appUser = appUserRepository.findById(appOrderDTO.getIdUser()).orElseThrow(() -> new AppUserNotFoundException(USER_NOT_FOUND));

        appOrder.setIdOrder( appOrderDTO.getIdOrder() );
        appOrder.setAmount( appOrderDTO.getAmount() );
        appOrder.setDate( appOrderDTO.getDate() );
        appOrder.setAppUser(appUser);
        appOrder.setProducts( productDTOListToProductList( appOrderDTO.getProducts() ) );

        return appOrder;
    }

    public AppOrderDTO toDto(AppOrder AppOrder) {
        if ( AppOrder == null ) {
            return null;
        }

        AppOrderDTO appOrderDTO = new AppOrderDTO();

        appOrderDTO.setIdOrder( AppOrder.getIdOrder() );
        appOrderDTO.setAmount( AppOrder.getAmount() );
        appOrderDTO.setDate( AppOrder.getDate() );
        appOrderDTO.setIdUser(AppOrder.getAppUser().getIdUser());
        appOrderDTO.setProducts( productListToProductDTOList( AppOrder.getProducts() ) );

        return appOrderDTO;
    }

    protected List<Product> productDTOListToProductList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( productMapper.toEntity( productDTO ) );
        }

        return list1;
    }

    protected List<ProductDTO> productListToProductDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDTO> list1 = new ArrayList<ProductDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productMapper.toDto( product ) );
        }

        return list1;
    }

}
