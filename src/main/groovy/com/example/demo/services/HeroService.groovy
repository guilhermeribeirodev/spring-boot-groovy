package com.example.demo.services

import com.example.demo.models.Hero
import com.example.demo.repositories.HeroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class HeroService {
  @Autowired
  HeroRepository heroRepository

  List findAll() {
    heroRepository.findAll().asList()
  }

  Hero findById(long id) {
    heroRepository.findById(id).orElse(null)
  }

  Hero findByIdOrError(long id) {
    heroRepository.findById(id).orElseThrow({
      new EntityNotFoundException()
    })
  }

  Hero save(Hero hero) {
    // assign hero to every abilities
    hero.abilities?.each { it.hero = hero }
    heroRepository.save(hero)
  }

  Hero update(Hero hero, long id) {
    Hero persisted = findByIdOrError(id)
    persisted.with {
      name = hero.name
    }
    def toBeRemoved = []
    // find values to update & delete
    persisted.abilities.each {
      def a = hero.abilities.find { it2 -> it2.id == it.id }
      if (a == null) toBeRemoved.add(it)
      else it.name = a.name
    }
    persisted.abilities.removeAll(toBeRemoved)
    // assign persisted entity as the hero
    hero.abilities.each {
      if (it.id == null) {
        it.hero = persisted
        persisted.abilities.add(it)
      }
    }

    heroRepository.save(persisted)
  }

  Hero deleteById(long id) {
    def hero = findByIdOrError(id)
    heroRepository.delete(hero)
    hero
  }
}
