package com.tanim.androidbasetemplate.di.component;

import com.tanim.androidbasetemplate.di.module.FragmentModule;
import com.tanim.androidbasetemplate.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = {AppComponent.class})
public
interface FragmentComponent {


}
