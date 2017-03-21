package me.skhu.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import me.skhu.domain.Comment;
import me.skhu.domain.dto.BoardPostDto;
import me.skhu.domain.dto.BoardPostInsertDto;
import me.skhu.service.BoardImageService;
import me.skhu.service.BoardPostService;
import me.skhu.service.CommentService;
import me.skhu.service.FileService;
import me.skhu.util.Pagination;
import me.skhu.util.Validator.BoardPostEditValidator;
import me.skhu.util.Validator.BoardPostValidator;

@Controller
@RequestMapping("board")
public class BoardPostController {

	@Autowired
	private BoardPostService boardPostService;

	@Autowired
	private FileService fileService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private BoardImageService boardImageService;

	@Autowired
	private BoardPostValidator boardPostValidator;

	@Autowired
	private BoardPostEditValidator boardPostEditValidator;

	@RequestMapping("list")
	public String list(Model model , Pagination pagination, @RequestParam("boardId") int boardId) {
		model.addAttribute("list", boardPostService.findAll(pagination,boardId));
		model.addAttribute("boardId",boardId);
		return "board/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model, Pagination pagination, int boardId ){
		model.addAttribute("boardId",boardId);
		return "board/write";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, int boardId, @ModelAttribute BoardPostInsertDto boardPostInsertDto, MultipartHttpServletRequest request, @RequestParam("file") MultipartFile[] files, BindingResult result) {
		boardPostValidator.validate(boardPostInsertDto,result);
		if(result.hasErrors()) {
			model.addAttribute("error",boardPostValidator.errorMesage(boardPostInsertDto));
			model.addAttribute("boardPost",boardPostInsertDto);
			return "board/write";
		}else {
			boardPostService.create(boardPostService.preInsert(boardPostInsertDto), files, request, boardId);
			return "redirect:/board/list?boardId=" + boardId;
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") int id , Model model, Pagination pagination){
    	model.addAttribute("boardPost",boardPostService.findById(id));
    	return "board/edit";
    }

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, Pagination pagination, @ModelAttribute BoardPostDto boardPostDto, BindingResult result) throws UnsupportedEncodingException{
		boardPostEditValidator.validate(boardPostDto,result);
		if(result.hasErrors()){
			model.addAttribute("error",boardPostEditValidator.errorMessage(boardPostDto));
			model.addAttribute("boardPost",boardPostDto);
			return "board/edit";
		}
    	boardPostService.update(boardPostDto);
    	return "redirect:/board/list";
    }

	@RequestMapping(value = "/read")
    public String read(@RequestParam("id") int id , Model model, Pagination pagination){
    	model.addAttribute("boardPost",boardPostService.findById(id));
    	model.addAttribute("fileList",fileService.findByBoardId(id));
    	model.addAttribute("comment",commentService.findByBoardId(id));
    	return "board/read";
    }

	@RequestMapping(value = "/delete")
    public String delete(@RequestParam("id") int id, Pagination pagination) throws UnsupportedEncodingException{
    	boardPostService.webDelete(id);
    	return "redirect:/board/list";
    }

	@RequestMapping(value ="/download")
	public void download(@RequestParam("id") int id, HttpServletResponse response) throws IOException{
		fileService.download(id,response);
	}

	@RequestMapping(value ="/commentSave", method = RequestMethod.POST)
	public @ResponseBody Comment commentSave(@RequestParam("comment") String comment, int boardId){
    	try{
    		return commentService.save(comment,boardId);
		}catch(Exception e){
    		return Comment.ofEmpty();
		}
	}

	@RequestMapping(value = "/commentDelete", method = RequestMethod.GET)
	public @ResponseBody String commentDelete(@RequestParam("id") int id){
		try {
			commentService.delete(id);
		}catch(Exception e){
			return "fail";
		}
		return "success";
	}
}
