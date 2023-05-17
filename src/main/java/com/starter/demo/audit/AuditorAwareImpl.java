package com.starter.demo.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Shiva Created on 09/04/23
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Test");
        // Can use Spring Security to return currently logged in user
//         return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
    }
}