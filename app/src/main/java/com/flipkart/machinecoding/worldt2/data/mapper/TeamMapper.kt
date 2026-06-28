package com.flipkart.machinecoding.worldt2.data.mapper

import com.flipkart.machinecoding.worldt2.data.dto.TeamDto
import com.flipkart.machinecoding.worldt2.domain.model.Team

fun TeamDto.toDomain(): Team {
    return Team(
        name = name,
        flagUrl = flag
    )
}