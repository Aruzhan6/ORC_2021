package com.pixel.meirlen.orc.rest;



public class ApiMethods {


    public static final String CATEGORIES_GET = "/api/v1/category/list";

    public static final String PRODUCT_GET = "/api/v1/product/filter";

    public static final String PRODUCTS_GET_BY_ID_CATEGORY = "/api/v1/product/filter/{id}";

    public static final String PRODUCT_GET_BY_ID= "/api/v1/product/{id}";


    public static final String ADD_REVIEW= "make/review/{id}";


    public static final String PRODUCT_GET_BY_PRODUCER = "/api/v1/product/filter";


    public static final String PRODUCT_ADD_FAV = "/api/v1/fav";

    public static final String PRODUCT_GET_FAV = "/api/v1/favs";

    public static final String SIGNUP = "/api/v1/customer/signup";

    public static final String SIGNIN = "/api/v1/customer/signin";

    public static final String CONFIRM = "/api/v1/customer/confirm";

    public static final String GET_PROFILE = "/api/v1/user/profile";

    public static final String CART_ADD = "/api/v1/cart/add";

    public static final String CART_DELETE= "/api/v1/cart/delete/{id}";

    public static final String CART_CLEAR= "/api/v1/cart/clean";

    public static final String BASKET_GET = "/api/v1/cart/get";

    public static final String HISTORY_GET = "/api/v1/purchase/history";


    public static final String DELETE_REQUEST = "/api/v1/purchase/cancel/{id}";

    public static final String SEND_ORDER = "/api/v1/purchase/buy";


    public static final String GET_DISCOUNTS = "/api/v1/discounts";


    public static final String CART_COUNT = "/api/v1/cart/count";

    public static final String QR_GET = "/api/v1/admin/user/companies";



}
