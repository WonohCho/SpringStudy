package kr.co.jwo.popup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.jwo.popup.dto.PopupDTO;
import kr.co.jwo.popup.service.IPopupService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/popup/doc")
@Controller
public class PopupController {

	@Autowired private IPopupService popupService = null;
	
	@RequestMapping(value="/list.god", method=RequestMethod.GET)
	public void list(Model model) {
		List<PopupDTO> list = popupService.list();
		log.debug("popupController의 list에서 list 뽑기 : " + list);
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="/view.god", method=RequestMethod.GET)
	public void goView(Model model, Integer popId) {
		PopupDTO popupDTO = popupService.view(popId);
		model.addAttribute("popupDTO", popupDTO);
	}
	
	@RequestMapping(value="/write.god", method=RequestMethod.GET)
	public void goWrite(Model model) {
		
	}
	
	@RequestMapping(value="/write.god", method=RequestMethod.POST)
	public void doWrite(Model model , PopupDTO popupDTO) {
		
		popupService.write(popupDTO);
	}
	
	
}
