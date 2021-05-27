package jp.co.internous.gaia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.gaia.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.gaia.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.gaia.model.session.LoginSession;

@Controller
@RequestMapping("/gaia/history")
public class PurchaseHistoryController {
	
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	@Autowired
	private LoginSession loginSession;

	
	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getUserId();
		List<PurchaseHistoryDto> historyList = purchaseHistoryMapper.findByUserId(userId);
		
		m.addAttribute("historyList", historyList);
		// page_header.htmlでsessionの変数を表示させているため、loginSessionも画面に送る
		m.addAttribute("loginSession", loginSession);
		
		return "purchase_history";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		int userId = loginSession.getUserId();
		int result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
		
		return result > 0;
	}
}
