package com.xavier.controller;

import com.xavier.annotation.WebController;
import com.xavier.model.FinanceModel;
import com.xavier.model.ProductModel;
import com.xavier.model.UserModel;
import com.xavier.service.CartService;
import com.xavier.service.FinanceService;
import com.xavier.service.ProductService;
import com.xavier.utils.DataTransferUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.annotation.Resource;
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
	@WebController
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
	@WebController
	public String login() {
		return "login";
	}

	/**
	 * 发布页面接口
	 *
	 * @return 返回发布页面
	 */
	@RequestMapping("/publish")
	@WebController
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
	@WebController
	public String publishSubmit(@RequestBody String productForm,
	                            HttpSession session,
	                            ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
		productService.publishProduct(productForm, map);
		return "publishSubmit";
	}

	/**
	 * 商品展示页面
	 * @param id 被展示的商品
	 * @param map model
	 * @return 展示页面
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	@WebController
	public String showProduct(@RequestParam(value = "id") Integer id,
	                          ModelMap map) {
		ProductModel productModel = productService.getProduct(id);
		FinanceModel financeModel = financeService.getFinanceModel(id);
		map.addAttribute("product", DataTransferUtil.toProductShowDTO(productModel, financeModel));
		return "show";
	}

	/**
	 * 购物车页面
	 * @param session 会话session
	 * @return 购物车页面
	 */
	@RequestMapping(value = "/settleAccount")
	@WebController
	public String cart(HttpSession session) {
		if (!verifyLogin(session)) {
			return "error";
		}
		return "settleAccount";
	}

	/**
	 * 财务页面
	 * @param session 会话session
	 * @param map model
	 * @return 财务页面
	 */
	@RequestMapping("/account")
	@WebController
	public String account(HttpSession session, ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
		List<FinanceModel> financeModels = financeService.listFinances();
		Long totalPrice = financeModels.stream().mapToLong(f -> f.getNum() * f.getPrice()).sum();
		logger.info("Buyer have bought these products: {}", financeModels);
		map.addAttribute("finances", financeModels);
		map.addAttribute("totalPrice", totalPrice);
		return "account";
	}

	/**
	 *
	 * @param id 被编辑商品的ID
	 * @param session 会话session
	 * @param map model
	 * @return 编辑页面
	 */
	@RequestMapping("/edit")
	@WebController
	public String edit(@RequestParam("id") int id, HttpSession session, ModelMap map) {
		if (!verifyLogin(session)) {
			return "error";
		}
		ProductModel productModel = productService.getProduct(id);
		map.addAttribute("product", productModel);
		return "edit";
	}

	/**
	 * 编辑成功页面
	 * @param id 被编辑商品的ID
	 * @param editProductForm 商品编辑后的内容表单
	 * @param session 会话session
	 * @param map model
	 * @return 编辑成功的页面
	 */
	@RequestMapping("/editSubmit")
	@Transactional
	@WebController
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

	/**
	 * 错误页面
	 * @return 错误页面
	 */
	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	/**
	 * 登录验证页面
	 * @param session 回话session
	 * @return true：已登录  false：游客
	 */
	private boolean verifyLogin(HttpSession session) {
		UserModel userModel = (UserModel) session.getAttribute("userModel");
		return userModel != null;
	}
}
