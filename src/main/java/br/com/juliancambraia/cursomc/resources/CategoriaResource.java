package br.com.juliancambraia.cursomc.resources;

import br.com.juliancambraia.cursomc.domain.Categoria;
import br.com.juliancambraia.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id) {
        Categoria categoria = this.categoriaService.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping
    public ResponseEntity<Void> inserir(@RequestBody Categoria categoria) {
        Categoria categoria1 = this.categoriaService.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(categoria1.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria categoria1 = this.categoriaService.update(categoria);

        return ResponseEntity.noContent().build();
    }
}
