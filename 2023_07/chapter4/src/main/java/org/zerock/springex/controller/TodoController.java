package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;


@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

//    @RequestMapping("/list")
//    public void list(Model model){
//
//        log.info("todo list.......");
//
//        //model.addAttribute("dtoList", todoService.getAll());
//    }

    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.......");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }


//    @GetMapping("/read")
//    public void read(Long tno, Model model){
//
//        TodoDTO todoDTO = todoService.getOne(tno);
//        log.info(todoDTO);
//
//        model.addAttribute("dto", todoDTO);
//
//    }

//    @GetMapping({"/read", "/modify"})
//    public void read(Long tno, Model model){
//
//        TodoDTO todoDTO = todoService.getOne(tno);
//        log.info(todoDTO);
//
//        model.addAttribute("dto", todoDTO );
//
//    }

    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model){

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO );

    }


//    @PostMapping("/remove")
//    public String remove(Long tno, RedirectAttributes redirectAttributes){
//
//        log.info("-------------remove------------------");
//        log.info("tno: " + tno);
//
//        todoService.remove(tno);
//
//        return "redirect:/todo/list";
//    }


//    @PostMapping("/remove")
//    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
//
//        log.info("-------------remove------------------");
//        log.info("tno: " + tno);
//
//        todoService.remove(tno);
//
//        redirectAttributes.addAttribute("page", 1);
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
//        return "redirect:/todo/list";
//    }

    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("-------------remove------------------");
        log.info("tno: " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list?" + pageRequestDTO.getLink();
    }


//    @PostMapping("/modify")
//    public String modify(@Valid TodoDTO todoDTO,
//                         PageRequestDTO pageRequestDTO,
//                         BindingResult bindingResult,
//                         RedirectAttributes redirectAttributes){
//
//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            redirectAttributes.addAttribute("tno", todoDTO.getTno() );
//            return "redirect:/todo/modify";
//        }
//
//        log.info(todoDTO);
//
//        todoService.modify(todoDTO);
//
//        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
//        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
//
//        return "redirect:/todo/list";
//    }


    @PostMapping("/modify")
    public String modify(
                        PageRequestDTO pageRequestDTO,
                        @Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("tno", todoDTO.getTno() );
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/todo/read";
    }


//    @PostMapping("/modify")
//    public String modify(@Valid TodoDTO todoDTO,
//                         BindingResult bindingResult,
//                         RedirectAttributes redirectAttributes){
//
//        if(bindingResult.hasErrors()) {
//            log.info("has errors.......");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
//            redirectAttributes.addAttribute("tno", todoDTO.getTno() );
//            return "redirect:/todo/modify";
//        }
//
//        log.info(todoDTO);
//
//        todoService.modify(todoDTO);
//
//        return "redirect:/todo/list";
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }


}
