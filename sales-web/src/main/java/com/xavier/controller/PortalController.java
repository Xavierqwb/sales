package com.xavier.controller;

import com.xavier.model.CartRecordModel;
import com.xavier.model.FinanceModel;
import com.xavier.model.ProductModel;
import com.xavier.model.UserModel;
import com.xavier.service.CartService;
import com.xavier.service.FinanceService;
import com.xavier.service.ProductService;
import com.xavier.utils.DataTransferUtil;
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
		List<ProductModel> allProducts = productService.listProduct();
		map.addAttribute("allProducts", allProducts);
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
	public String publish(HttpSession session) {
		if (!verifyLogin(session)) {
			return "error";
		}
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
	                            HttpSession session,
	                            ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
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
					List<CartRecordModel> cartRecordModelList =
						JsonUtils.readList(value, CartRecordModel.class);
					logger.info("Add records to cart {}.", cartRecordModelList);
					cartService.addProductsToCart(cartRecordModelList);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}

		ProductModel productModel = productService.getProduct(id);
		FinanceModel financeModel = financeService.getFinanceModel(id);
		map.addAttribute("product", DataTransferUtil.toProductShowDTO(productModel, financeModel));
		return "show";
	}

	@RequestMapping(value = "/settleAccount")
	public String cart(HttpServletResponse response, HttpSession session) {
		if (!verifyLogin(session)) {
			return "error";
		}
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
	public String account(HttpSession session, ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
		List<FinanceModel> financeModels = financeService.listFinances();
		Long totalPrice = financeModels.stream().mapToLong(f -> f.getNum() * f.getPrice()).sum();
		logger.info("{}", financeModels);
		map.addAttribute("finances", financeModels);
		map.addAttribute("totalPrice", totalPrice);
		return "account";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam("id") int id, HttpSession session, ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
		ProductModel productModel = productService.getProduct(id);
		map.addAttribute("product", productModel);
		return "edit";
	}

	@RequestMapping("/editSubmit")
	public String editSubmit(@RequestParam("id") int id, @RequestBody String editProductForm,
	                         HttpSession session, ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
		ProductModel productModel = ProductModel.parseProduct(editProductForm);
		productService.editProduct(id, productModel);
		logger.info("edit product: {}", productModel);
		map.addAttribute("id", id);
		return "editSubmit";
	}

	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	private boolean verifyLogin(HttpSession session) {
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		return userModel != null;
	}
}
