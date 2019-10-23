package com.itheima.web.servlet;

import com.itheima.bean.Cart;
import com.itheima.bean.CartItem;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends BaseServlet {

    /**
     * 添加到购物车
     */
    public String addToCart(HttpServletRequest request, HttpServletResponse response){

        try {
            String pid = request.getParameter("pid");
            int count = Integer.parseInt(request.getParameter("count"));

            ProductService productService = new ProductServiceImpl();
            Product product = productService.findByPid(pid);

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCount(count);

            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if(cart==null){
                cart = new Cart();
            }
            cart.addToCart(cartItem);
            request.getSession().setAttribute("cart",cart);

            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从购物车移除购物项(根据pid)
     */
    public String  removeFromCart(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String pid = request.getParameter("pid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        cart.removeFromCart(pid);
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }

    /**
     * 清空购物车
     */
    public String  clearCart(HttpServletRequest request,HttpServletResponse response) throws Exception {
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.clearCart();
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }
}
