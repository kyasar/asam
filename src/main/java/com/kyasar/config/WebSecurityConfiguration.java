/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kyasar.config;

import com.kyasar.service.AppUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.kyasar")
@EnableGlobalMethodSecurity(prePostEnabled=true, proxyTargetClass=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication()
				.withUser("john").password("123").roles("USER")
				.and()
				.withUser("kadir").password("123").roles("ADMIN");
		*/
		auth.userDetailsService(appUserDetailsService);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean()
			throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated();
	}

}
