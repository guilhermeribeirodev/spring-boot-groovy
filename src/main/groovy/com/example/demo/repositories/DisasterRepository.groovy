package com.example.demo.repositories

import com.example.demo.models.Disaster
import org.springframework.data.repository.PagingAndSortingRepository

// we may need paging and sorting
interface DisasterRepository extends PagingAndSortingRepository<Disaster, Long> {}