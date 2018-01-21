package com.lab409.crowdingsourcing.dto;

import org.springframework.stereotype.Component;

@Component
public class SimpleRequirementDto {

        private Long requirement_id;
        private String requirement_name;
        private String requirement_type;
        private int requirement_state;

        public Long getRequirement_id() {
            return requirement_id;
        }

        public void setRequirement_id(Long requirement_id) {
            this.requirement_id = requirement_id;
        }

        public int getRequirement_state() {
            return requirement_state;
        }

        public void setRequirement_state(int requirement_state) {
            this.requirement_state = requirement_state;
        }

        public String getRequirement_type() {
            return requirement_type;
        }

        public void setRequirement_type(String requirement_type) {
            this.requirement_type = requirement_type;
        }

        public String getRequirement_name() {
            return requirement_name;
        }

        public void setRequirement_name(String requirement_name) {
            this.requirement_name = requirement_name;
        }

}
