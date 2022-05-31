package com.renaldo.dto;

import com.renaldo.pojo.Combo;
import com.renaldo.pojo.ComboDish;
import lombok.Data;
import java.util.List;

@Data
public class ComboDto extends Combo {

    private List<ComboDish> comboDishes;

    private Long categoryId;
}
