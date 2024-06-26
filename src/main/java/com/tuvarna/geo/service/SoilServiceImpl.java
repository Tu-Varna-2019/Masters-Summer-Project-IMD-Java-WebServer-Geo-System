package com.tuvarna.geo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuvarna.geo.entity.Soil;
import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.repository.SoilRepository;
import com.tuvarna.geo.service.dto.RestApiResponse;
import com.tuvarna.geo.service.dto.risk.RiskDTO;

import jakarta.transaction.Transactional;

@Service
public class SoilServiceImpl implements SoilService {
    private static final Logger logger = LogManager.getLogger(SoilServiceImpl.class.getName());

    private SoilRepository soilRepository;

    @Autowired
    public SoilServiceImpl(SoilRepository soilRepository) {
        this.soilRepository = soilRepository;
    }

    @Override
    @Transactional
    @SuppressWarnings({ "squid:S3457", "squid:S2629" })
    public RestApiResponse<Soil> getSoil(RiskDTO riskDTO) {

        Soil soilDb = soilRepository.findByLongitudeAndLatitude(riskDTO.getLongitude(),
                riskDTO.getLatitude());
        if (soilDb == null)
            throw new BadRequestError("Error, soil type not found!");

        logger.info(
                "Soil type found from given longitude/langiude {}, {}",
                riskDTO.getLongitude(), riskDTO.getLatitude());
        logger.info("Now sending soil type data with guid: {}", soilDb.getGid());

        return new RestApiResponse<>(soilDb, "Soil type found with gid: " + soilDb.getGid(), 201);
    }

}
