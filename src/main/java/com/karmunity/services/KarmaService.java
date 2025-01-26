package com.karmunity.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.karmunity.models.Karma;
import com.karmunity.repositories.KarmaRepository;

@Service
public class KarmaService {

    private final KarmaRepository karmaRepository;

    @Autowired
    public KarmaService(KarmaRepository karmaRepository) {
        this.karmaRepository = karmaRepository;
    }

    public Karma createKarma(Karma karma) {
        return karmaRepository.save(karma);
    }
}