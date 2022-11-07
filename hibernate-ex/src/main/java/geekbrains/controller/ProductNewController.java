package geekbrains.controller;


import geekbrains.ProductSpecifications;
import geekbrains.entities.Product;
import geekbrains.exception.NotFoundException;
import geekbrains.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/products")
class ProductNewController {

    private final ProductRepository productRepository;

    public ProductNewController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public String listPage(Model model,
                           @RequestParam("titleFilter") Optional<String> titleFilter,
                           @RequestParam("minPriceFilter") Optional<BigDecimal> minPriceFilter,
                           @RequestParam("maxPriceFilter") Optional<BigDecimal> maxPriceFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sort") Optional<String> sort) {

        Specification<Product> spec = Specification.where(null);
        if (titleFilter.isPresent() && !titleFilter.get().isEmpty())
            spec = spec.and(ProductSpecifications.productPrefix(titleFilter.get()));
        if (minPriceFilter.isPresent())
            spec = spec.and(ProductSpecifications.minPrice(minPriceFilter.get()));
        if (maxPriceFilter.isPresent())
            spec = spec.and(ProductSpecifications.maxPrice(maxPriceFilter.get()));

        if (sort.isPresent() && !sort.get().isEmpty()) { // проверка на наличие сортировки
            model.addAttribute("products", productRepository.findAll(spec,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(3), Sort.by(sort.get()).ascending())));
        } else {
            model.addAttribute("products", productRepository.findAll(spec,
                    PageRequest.of(page.orElse(1) - 1, size.orElse(3))));
        }
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "products-add";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        return "products-add";
    }

    @PostMapping
    public String update(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "products-add";
        }

        productRepository.save(product);
        return "redirect:/products";
    }

    // удаление продукта
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        productRepository.deleteById(id);
        return "redirect:/products";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("not-found");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}


