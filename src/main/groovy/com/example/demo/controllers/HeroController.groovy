package com.example.demo.controllers

import com.example.demo.models.Hero
import com.example.demo.services.HeroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.transaction.Transactional

@RestController
@RequestMapping('hero')
@Transactional
class HeroController {
  @Autowired
  HeroService heroService

  @GetMapping('')
  List findAll() {
    heroRepository.findAll(Sort.by('name')).asList()
  }

  @GetMapping('{id}')
  Hero findOne(@PathVariable long id) {
    heroService.findById(id)
  }

  @PostMapping('')
  Hero save(@RequestBody Hero hero) {
    heroService.save(hero)
  }

  @PutMapping('{id}')
  Hero update(@RequestBody Hero hero, @PathVariable long id) {
    heroService.update(hero, id)
  }

  @DeleteMapping('{id}')
  Hero deleteById(@PathVariable long id) {
    heroService.deleteById(id)
  }
}
