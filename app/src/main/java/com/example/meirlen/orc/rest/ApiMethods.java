package com.example.meirlen.orc.rest;



public class ApiMethods {

    public static final String CATEGORIES_GET = "/public/api/v1/category/list";

    public static final String PRODUCT_GET = "/public/api/v1/product/filter";

    public static final String PRODUCTS_GET_BY_ID_CATEGORY = "/public/api/v1/product/filter/{id}";

    public static final String PRODUCT_GET_BY_ID= "/public/api/v1/product/{id}";

    public static final String ADD_REVIEW= "make/review/{id}";


    public static final String PRODUCT_GET_BY_PRODUCER = "/public/api/v1/product/filter";

//
    public static final String PRODUCT_ADD_FAV = "/public/api/v1/fav";

    public static final String PRODUCT_GET_FAV = "/public/api/v1/favs";

    public static final String SIGNUP = "/public/api/v1/customer/signup";

    public static final String SIGNIN = "/public/api/v1/customer/signin";

    public static final String CONFIRM = "/public/api/v1/customer/confirm";

    public static final String GET_PROFILE = "/public/api/v1/user/profile";

    public static final String CART_ADD = "/public/api/v1/cart/add";

    public static final String CART_DELETE= "/public/api/v1/cart/delete/{id}";

    public static final String CART_CLEAR= "/public/api/v1/cart/clean";

    public static final String BASKET_GET = "/public/api/v1/cart/get";

    public static final String HISTORY_GET = "/public/api/v1/purchase/history";


    public static final String DELETE_REQUEST = "/public/api/v1/purchase/cancel/{id}";

    public static final String SEND_ORDER = "/public/api/v1/purchase/buy";


    public static final String GET_DISCOUNTS = "/public/api/v1/discounts";


    public static final String CART_COUNT = "/public/api/v1/cart/count";

    public static final String QR_GET = "/public/api/v1/my/suppliers";



}
