package com.pixel.meirlen.orc.helper;

import com.pixel.meirlen.orc.model.Producer;
import com.pixel.meirlen.orc.model.Product;
import com.pixel.meirlen.orc.model.ProductImage;

import java.util.ArrayList;
import java.util.List;

public class FakeUtiill {

    public static Product getFakeProduct() {
        Product product = new Product();
        //Property
        product.setProductName("Пицца Пепперони");
        product.setProductDescription("Пицца Пепперони – это довольно популярная разновидность среди всех прочих пицц. Название она свое получила благодаря основному компоненту, а именно благодаря острой колбаске. Называется она, как Вы уже, наверное, догадались, Пепперони.");
        product.setProductWeight("500");
        product.setProductSize("1200");
        product.setProductPrice(1200);
        //Images
        List<ProductImage> images = new ArrayList<>();
        images.add(new ProductImage("http://www.marieclaire.ru/images/th/2083/4765/7034/1000@745@476570349d87b24041f59952f66a6c10-NmNjN2YyYzhjMg.jpg?time=167"));
        images.add(new ProductImage("https://milograd.ru/wa-data/public/shop/products/50/07/750/images/827/827.970.jpg"));
        images.add(new ProductImage("https://avatars.mds.yandex.net/get-mpic/372220/img_id2752250570412045771.jpeg/9hq"));
        product.setImages(images);
        //Producer
        Producer producer=new Producer();
        producer.setProducerName("Pixel 2018");
        producer.setProducerAddress("Алиханова 37, оф 510");
        producer.setProducerTel1("8 778 940 55 30");
        product.setProducer(producer);

        return product;
    }

}
