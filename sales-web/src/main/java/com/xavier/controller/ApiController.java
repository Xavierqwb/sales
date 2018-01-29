package com.xavier.controller;

import com.xavier.common.BaseResponse;
import com.xavier.model.BuyModel;
import com.xavier.model.CartRecordModel;
import com.xavier.service.CartService;
import com.xavier.service.LoginService;
import com.xavier.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xavier on 2018/1/22.
 */
@Controller
@RequestMapping("/sales/api")
@Transactional
public class ApiController {

	private final Logger logger = LoggerFactory.getLogger(ApiController.class);

	private static final String IMAGE_DIR_PATH = "sales-web/src/main/resources/static/image/";

	private static final String RETURN_IMAGE_PATH_PREFIX = "../image/";

	private static final Random RANDOM = new Random(17);

	@Resource
	private LoginService loginService;

	@Resource
	private CartService cartService;

	@Resource
	private ProductService productService;

	/**
	 * 登录认证接口
	 *
	 * @param name         用户名
	 * @param password     md5加密后的密码
	 * @param httpRequest  HttpServletRequest对象，用来提取Session
	 * @param httpResponse HttpServletResponse对象，用来返回Cookie
	 * @return 返回认证结果
	 */
	@RequestMapping("/login")
	@ResponseBody
	public BaseResponse login(@RequestParam(value = "userName") String name,
	                          @RequestParam(value = "password") String password,
	                          HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		BaseResponse baseResponse = new BaseResponse();

		HttpSession session = httpRequest.getSession();
		if (loginService.login(name, password, session)) {
			logger.info("User[{}] logged in.", name);
			session.setMaxInactiveInterval(1800);
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(1800);
			httpResponse.addCookie(cookie);

			baseResponse.setMessage("登录成功");
			baseResponse.setSuccess(true);
			baseResponse.setCode(200);
			return baseResponse;
		}
		baseResponse.setSuccess(false);
		return baseResponse;
	}

	/**
	 * 退出登录接口，退出清除session标志
	 *
	 * @param request HttpServletRequest对象，用来提取Session
	 */
	@RequestMapping("/quit")
	public void quit(HttpServletRequest request) {
		loginService.quit(request.getSession());
	}

	@RequestMapping("/buy")
	@ResponseBody
	public BaseResponse buy(@RequestBody List<BuyModel> buyModelList) {
		BaseResponse baseResponse = new BaseResponse();
		buyModelList = buyModelList.stream()
			.filter(buyModel -> buyModel.getNumber() > 0).collect(Collectors.toList());
		cartService.buyProducts(buyModelList);
		productService.soldProducts(buyModelList);
		baseResponse.setCode(200);
		baseResponse.setSuccess(true);
		return baseResponse;
	}

	/**
	 * 删除商品的接口
	 * @param id 被删除商品的ID
	 * @return json串
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public BaseResponse deleteProduct(@RequestParam("id") int id) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setCode(0);
		baseResponse.setSuccess(false);
		if (productService.deleteProduct(id)) {
			baseResponse.setSuccess(true);
			baseResponse.setCode(200);
			baseResponse.setMessage("删除成功");
		}
		return baseResponse;
	}

	/**
	 * 上传图片的接口
	 * @param multipartFile 上传的文件
	 * @return json串
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public BaseResponse<String> upload(@RequestParam("file") MultipartFile multipartFile) {
		BaseResponse<String> baseResponse = new BaseResponse<>();
		baseResponse.setCode(0);
		if (multipartFile != null && multipartFile.getSize() > 0) {
			String originFileName = multipartFile.getOriginalFilename();
			String fileName = System.currentTimeMillis() + RANDOM.nextInt(100)
			                  + originFileName.substring(originFileName.lastIndexOf("."));
			try {
				File file = new File(IMAGE_DIR_PATH + fileName);
				file = file.getAbsoluteFile();
				multipartFile.transferTo(file);
				logger.info("Save file: {}", fileName);
			} catch (IOException e) {
				return baseResponse;
			}
			baseResponse.setCode(200);
			baseResponse.setData(RETURN_IMAGE_PATH_PREFIX + fileName);
		}
		return baseResponse;
	}

	@RequestMapping("/addToCart")
	@ResponseBody
	public BaseResponse addToCart(@RequestBody CartRecordModel cartRecordModel) {
		BaseResponse baseResponse = new BaseResponse();
		logger.info("Add {} to cart.", cartRecordModel);
		cartService.addProductToCart(cartRecordModel);
		baseResponse.setCode(200);
		baseResponse.setSuccess(true);
		return baseResponse;
	}

	@RequestMapping("/getCart")
	@ResponseBody
	public BaseResponse<List> getCart() {
		BaseResponse<List> baseResponse = new BaseResponse<>();
		List<CartRecordModel> cartRecordModels = cartService.listProductsInCart();
		baseResponse.setSuccess(true);
		baseResponse.setCode(200);
		baseResponse.setData(cartRecordModels);
		return baseResponse;
	}
}
