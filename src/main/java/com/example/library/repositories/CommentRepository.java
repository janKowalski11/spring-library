package com.example.library.repositories;
/*
Author: BeGieU
Date: 15.02.2019
*/

import com.example.library.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long>
{
}
