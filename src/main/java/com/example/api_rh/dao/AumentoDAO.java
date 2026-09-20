package com.example.api_rh.dao;

import java.time.OffsetDateTime;

public interface AumentoDAO {

    int exec(double percentual);

    void save(OffsetDateTime data, double percentual, Long usuarioId);
}
