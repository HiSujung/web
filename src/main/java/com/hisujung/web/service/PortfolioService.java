package com.hisujung.web.service;

import com.hisujung.web.dto.PortfolioResponseDto;
import com.hisujung.web.dto.PortfolioSaveRequestDto;
import com.hisujung.web.dto.PortfolioUpdateRequestDto;
import com.hisujung.web.entity.Member;
import com.hisujung.web.entity.Portfolio;
import com.hisujung.web.jpa.MemberRepository;
import com.hisujung.web.jpa.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PortfolioService {

    private final MemberRepository memberRepository;
    private final PortfolioRepository portfolioRepository;

    @Transactional
    public Long save(PortfolioSaveRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id = " + requestDto.getMemberId()));

        return portfolioRepository.save(requestDto.toEntity(member)).getId();
    }

    @Transactional
    public Long update(Long id, PortfolioUpdateRequestDto requestDto) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포트폴리오가 없습니다. id=" + id));

        portfolio.update(requestDto.getTitle(), requestDto.getUrlLink(), requestDto.getDescription());

        return id;
    }

    public PortfolioResponseDto findById(Long id) {
        Portfolio entity = portfolioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포트폴리오가 없습니다. id" + id));

        return new PortfolioResponseDto(entity);
    }
}
