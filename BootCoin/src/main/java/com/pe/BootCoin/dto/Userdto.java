package com.pe.BootCoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
    private String idClient;
    private String phoneNumber;
    private String documentType;
    private String email;
}
