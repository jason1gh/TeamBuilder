/**
 *  Copyright information...
 */
package com.willthan;

import org.junit.Before;
import org.junit.Test;
import com.willthan.TeamBuilder;

import static org.assertj.core.api.Assertions.assertThat;


public class TeamBuilderTest {

    private TeamBuilder teamBuilder;

    @Before
    public void setup() {
        teamBuilder = new TeamBuilder();
    }


    @Test
    public void testSpecialLoationsEmptyInput() throws Exception {
        // Test empty input
        String[] paths = {};
        int[] results = teamBuilder.specialLoations(paths);

        assertThat(results[0]).isEqualTo(-1);
        assertThat(results[1]).isEqualTo(-1);
    }

    @Test
    public void testSpecialLoationsInvalidInput() throws Exception {
        // Test invalid input
        String[] paths = {"1101011111", "011010", "001100", "001110", "010011", "000011"};
        int[] results = teamBuilder.specialLoations(paths);

        assertThat(results[0]).isEqualTo(-1);
        assertThat(results[1]).isEqualTo(-1);
    }

    @Test
    public void testSpecialLoations1() throws Exception {
        // Test correct input
        String[] paths = {
                "110101", "011010", "001100", "001110", "010011", "000011"
        };
        int[] results = teamBuilder.specialLoations(paths);

        assertThat(results[0]).isEqualTo(1);
        assertThat(results[1]).isEqualTo(5);

    }

    @Test
    public void testSpecialLoations2() throws Exception {
        // Test correct input 2
        String[] paths = {
              "110001", "110000", "001100", "001110", "010110", "101001"
        };
        int[] results = teamBuilder.specialLoations(paths);

        assertThat(results[0]).isEqualTo(6);
        assertThat(results[1]).isEqualTo(6);
    }

}
