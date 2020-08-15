package com.yyb.service;

import com.yyb.model.User1;

public interface User1Service {
    void addRequired(User1 user);
    void addRequiredNoTrans(User1 user);

    //====================
    void addRequiresNew(User1 user);

    //====================
    void addNested(User1 user);
}
