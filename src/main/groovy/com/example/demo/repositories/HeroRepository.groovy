package com.example.demo.repositories

import com.example.demo.models.Hero
import org.springframework.data.repository.PagingAndSortingRepository

interface HeroRepository extends PagingAndSortingRepository<Hero, Long> {

    List<Hero> findtop3By()
}