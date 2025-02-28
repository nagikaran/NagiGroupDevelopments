package com.NagiGroup.dto.load;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoadDto {
	
	private int load_id;
	private String load_number;
	private String source;
	private String destination;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  pick_up_date; // timestamp without time zone,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  delievery_date; // timestamp without time zone,
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  earliest_time_arrival; // timestamp without time zone,
	private int driver_id;
	private String driver_name;
	private double base_price;
	private double final_price;
	private String file_name;
	
	
	

}
