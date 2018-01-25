package com.xavier.controller;

import com.xavier.model.CartRecordModel;
import com.xavier.model.FinanceModel;
import com.xavier.model.ProductModel;
import com.xavier.service.CartService;
import com.xavier.service.FinanceService;
import com.xavier.service.ProductService;
import com.xavier.utils.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 主站页面控制器
 * Created by xavier on 2018/1/21.
 */
@Controller
@RequestMapping("/sales")
public class PortalController {

	private Logger logger = LoggerFactory.getLogger(PortalController.class);

	@Resource
	private ProductService productService;

	@Resource
	private CartService cartService;

	@Resource
	private FinanceService financeService;

	/**
	 * 首页接口
	 *
	 * @return 返回首页
	 */
	@RequestMapping("")
	public String homePage(
		@RequestParam(value = "type", required = false, defaultValue = "0") int type,
		ModelMap map) {
		map.addAttribute("notBuyFlag", type);
		List<ProductModel> notBoughtProductModelList;
		List<ProductModel> boughtProductModelList;
		notBoughtProductModelList = productService.notBoughtProductList();
		boughtProductModelList = productService.boughtProductList();
		map.addAttribute("notBoughtProducts", notBoughtProductModelList);
		map.addAttribute("boughtProducts", boughtProductModelList);
		return "index";
	}

	/**
	 * 登录页面接口
	 *
	 * @return 返回登录页面
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	/**
	 * 发布页面接口
	 *
	 * @return 返回发布页面
	 */
	@RequestMapping("/publish")
	public String publish() {
		return "publish";
	}

	/**
	 * 发布成功页面接口
	 *
	 * @param productForm 传入的参数，表单格式
	 * @param map         ModelMap，向前端传递参数
	 * @return 发布成功页面
	 */
	@RequestMapping(value = "/publishSubmit", method = RequestMethod.POST)
	public String publishSubmit(@RequestBody String productForm,
	                            ModelMap map) {
		logger.info(productForm);
		productService.publishProduct(productForm, map);
		return "publishSubmit";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showProduct(@RequestParam(value = "id") Integer id,
	                          HttpServletRequest request,
	                          HttpServletResponse response,
	                          ModelMap map) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("productsAddToCart")) {
					String value = cookie.getValue();
					try {
						value = URLDecoder.decode(value, "utf-8");
						logger.info("{}", value);
					} catch (UnsupportedEncodingException e) {
						logger.info("{}", e);
						// ignore
					}
					List<CartRecordModel>
						cartRecordModelList =
						JsonUtils.readList(value, CartRecordModel.class);
					logger.info("Add records to cart {}.", cartRecordModelList);
					cartService.addProductsToCart(cartRecordModelList);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		productService.getProduct(id, map);
		return "show";
	}

	@RequestMapping(value = "/settleAccount")
	public String cart(HttpServletResponse response) {
		List<CartRecordModel> cartRecordModels = cartService.listProductsInCart();
		String json = JsonUtils.toJson(cartRecordModels);
		Cookie cookie = null;
		try {
			cookie = new Cookie("products", URLEncoder.encode(json, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.info("{}", e);
		}
		logger.info("{}", json);
		response.addCookie(cookie);
		return "settleAccount";
	}

	@RequestMapping("/account")
	public String account(ModelMap map) {
		List<FinanceModel> financeModels = financeService.listFinances();
		Long totalPrice = financeModels.stream().mapToLong(f -> f.getNum() * f.getPrice()).sum();
		logger.info("{}", financeModels);
		map.addAttribute("finances", financeModels);
		map.addAttribute("totalPrice", totalPrice);
		return "account";
	}

	/**
	 * 验证登录，在FreeMarker中不需要，FreeMarker会自动将session中的属性加入到model中，如果再在model中加入重复属性会出bug
	 * 使用ThymeLeaf的话就需要验证登录
	 *
	 * @param session HTTPSession，会话对象，从中取出isLogin判断登录状态
	 * @param map     ModelMap对象，存入属性传给前端模板
	 */
	private void verifyLogin(HttpSession session, ModelMap map) {
		Boolean isLogin = (Boolean) session.getAttribute("isLogin");
		if (isLogin != null && isLogin) {
			if (!map.containsAttribute("isLogin")) {
				map.addAttribute("isLogin", true);
			}
			if (!map.containsAttribute("name")) {
				map.addAttribute("name", session.getAttribute("title"));
			} else {
				map.replace("name", session.getAttribute("title"));
			}
		}
	}
}
