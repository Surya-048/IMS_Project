package com.inventory.service;

import com.inventory.dto.ProductsDto;
import com.inventory.dto.OrdersDto;
import com.inventory.exception.GenericException;
import com.inventory.model.OrderProductDetails;
import com.inventory.model.Orders;
import com.inventory.model.Products;
import com.inventory.model.Seller;
import com.inventory.repository.OrderProductDetailsRepo;
import com.inventory.repository.OrdersRepo;
import com.inventory.repository.ProductsRepo;
import com.inventory.repository.SellerRepo;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderProductDetailsRepo orderProductDetailsRepo;


    //Find All Products Details according to Admin..
    public List<ProductsDto> allProductsDetails(Long id){
        List<Products> products = this.productsRepo.findByAdminId(id);
//        System.out.println(products);
        return products.stream().map(p-> this.modelMapper.map(p,ProductsDto.class)).toList();
    }

    public List<ProductsDto> allProducts(){
        List<Products> products = this.productsRepo.findAll();
//        System.out.println(products);
        return products.stream().map(p-> this.modelMapper.map(p,ProductsDto.class)).toList();
    }



    //Add new product..
    public ProductsDto addNewProduct(ProductsDto productsDto){
        Products products = this.modelMapper.map(productsDto,Products.class);
//        products.setActive(true);
        products.setCreatedAt(new Date());
        products.setUpdatedAt(new Date());
        products.setAdminId(productsDto.getAdminId());
        Products products1 = this.productsRepo.save(products);
        return this.modelMapper.map(products1,ProductsDto.class);
    }

    //Update Product..
    public ProductsDto updateProduct(ProductsDto productsDto){
        Products products = null ;
        try{
            products = this.productsRepo.findById(productsDto.getProductId()).get();
        }catch (NoSuchElementException e){
            throw new GenericException("This Product Doesn't Exits. ");
        }

        Date date = products.getCreatedAt();
        products = this.modelMapper.map(productsDto, Products.class);
        products.setCreatedAt(date);
        products.setUpdatedAt(new Date());
        Products products1 = this.productsRepo.save(products);
        return this.modelMapper.map(products1,ProductsDto.class);
    }


    //Add New Order..
    public void addNewOrder(OrdersDto ordersDto){
        Seller seller = this.sellerRepo.findById(ordersDto.getAdminId().intValue()).get();
        if(seller != null){
            Orders orders = this.modelMapper.map(ordersDto,Orders.class);
            Date date = new Date();
            orders.setCreatedAt(date);
            this.ordersRepo.save(orders);
            Orders orders1 = this.ordersRepo.findByCreatedAt(date);
            ordersDto.getProducts().stream()
                    .map(p -> {
                        OrderProductDetails orderProductDetails = this.modelMapper.map(p, OrderProductDetails.class);
                        orderProductDetails.setOrders(orders1);
                        Products products = this.productsRepo.findById(p.getProductId()).get();
                        orderProductDetails.setProductId(products);
                        return orderProductDetails;
                    }).forEach(this.orderProductDetailsRepo::save);
        }

    }
}
