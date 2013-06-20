/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdfadapter;

/**
 *
 * @author user
 */
public class Title {

        private String titleText;
        private int scale;

        // scale is measurement of the importance of the title range is from 1 to 5 default value is 3.(Should discuss and finalized)
        public Title(String titleText) {
                // TODO Auto-generated constructor stub
                this.titleText = titleText;
                this.scale = 3;
        }

        public Title(String titleText, int scale) {
                // TODO Auto-generated constructor stub
                this.titleText = titleText;
                this.scale = scale;
        }

        public String getTitleText() {
                return titleText;
        }

        public void setTitleText(String titleText) {
                this.titleText = titleText;
        }

        public int getScale() {
                return scale;
        }

        public void setScale(int scale) {
                this.scale = scale;
        }

}

