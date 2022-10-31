package geekbrains.controller;


import geekbrains.entities.Product;
import geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/oldProducts")
public class ProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping
//    public List<Product> getAllByCostBetween(@RequestParam(required = false, name = "minCost") BigDecimal minCost,
//                                             @RequestParam(required = false, name = "maxCost") BigDecimal maxCost)
//    { if (minCost==null && maxCost == null) {return productService.getAll();}
//        if (minCost==null) {return productService.getAllByCostLessThan(maxCost);}
//        if (maxCost==null) {return productService.getAllByCostGreaterThan(minCost);}
//
//        return productService.getAllByCostBetween(minCost, maxCost);}
//
//    @GetMapping("/id:{id}")
//    public Product getById(@PathVariable long id){return productService.getById(id);}
//
//    @GetMapping("/title:{title}")
//    public List<Product> getProductByTitleContains(@PathVariable String title){return productService.getAllByTitleContains(title);}
//
//    @PostMapping
//    public Product save(@RequestBody Product product){return productService.save(product);}
//
//    @GetMapping("/delete/{id}")
//    public void deleteById(@PathVariable long id){productService.deleteById(id);}
}
