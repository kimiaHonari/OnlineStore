package ir.onlinestore.authentication;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Created by kimia on 1/4/2017.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }


    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            System.out.println("reqURI  "+reqURI);

           /* if (reqURI.equals("/OnlineStore/Login.xhtml")
                    || (ses != null && ses.getAttribute("username") != null)|| reqURI.equals("/OnlineStore/Home.xhtml")
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else if (ses != null && ses.getAttribute("username") != null && (Boolean) ses.getAttribute("access"))
               */
            if((ses != null && ses.getAttribute("username") != null)){

                if(reqURI.contains("/OnlineStore/Manage")&& !(Boolean)ses.getAttribute("access")){
                    resp.sendRedirect(reqt.getContextPath() + "/OnlineStore/Home.xhtml");
                }
                else{
                    chain.doFilter(request, response);
                }
            }
            else if(reqURI.equals("/OnlineStore/Home.xhtml")||reqURI.equals("/OnlineStore/Login.xhtml")
                    ||reqURI.equals("/OnlineStore/Carts.xhtml")||(reqURI.equals("/OnlineStore/Product.xhtml"))
                    || reqURI.contains("javax.faces.resource")||(reqURI.equals("/OnlineStore/ProductDetails.xhtml"))|| (reqURI.equals("/OnlineStore/MyCart.xhtml"))){
                chain.doFilter(request, response);
            }
            else
                resp.sendRedirect(reqt.getContextPath() + "/OnlineStore/Login.xhtml");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void destroy() {

    }
}