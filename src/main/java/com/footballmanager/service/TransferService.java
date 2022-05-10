package com.footballmanager.service;

import java.util.List;

public interface TransferService {
    void transfer(Long fromId, Long toId, List<Long> playerIds);
}
