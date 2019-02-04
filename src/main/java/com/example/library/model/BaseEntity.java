package com.example.library.model;
/*
Author: BeGieU
Date: 03.02.2019
*/

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Comparable<BaseEntity>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public boolean isNew()
    {
        return this.id == null;
    }

    @Override
    public int compareTo(BaseEntity other)
    {
        if (other == null)
        {
            return 1;
        } //satisfies  null  requirement
        return this.id > other.id ? 1 :
                this.id < other.id ? -1 : 0;
    }
}
