package com.project.bms.controller;

import com.project.bms.model.BookDTO;
import com.project.bms.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.bms.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping({"", "/"})
    public String showBooks(Model model) {
        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("books", books);
        return "/books/index";
    }

    @GetMapping("/create")
    public String showCreateBook(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("bookDTO", bookDTO);
        return "/books/create";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute BookDTO bookDTO, BindingResult result) throws IOException {
        if (bookDTO.getImage().isEmpty()){
            result.addError(new FieldError("bookDTO", "image", "Image is required"));
        }
        if (result.hasErrors()){
            return "/books/create";
        }

        //save image
        MultipartFile image = bookDTO.getImage();
        Date createAt = new Date();
        String filename = createAt.getTime() + "_" + image.getOriginalFilename();
        try{
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                try {
                    Files.createDirectories(uploadPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try (InputStream inputStream = image.getInputStream()){
                Path filePath = uploadPath.resolve(filename);
                Files.copy(inputStream, Paths.get(uploadDir+filename));
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setImage(filename);
        bookRepository.save(book);
        return "redirect:/books";
    }
}
