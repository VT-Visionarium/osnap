ecmascript:
//-------------------------------------------------------------------------------
// Copyright 2014 Virginia Tech Visionarium
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//-------------------------------------------------------------------------------

var js_y = 0.0;

function button_press_1(value, time) {

    if (value) {
    //nav.message = 'decreaseNavSpeed';
        if (colorChoice == -1) {
            colorChoice = 0;
        } else {
            colorChoice = -1;
        }
    }

}


function button_press_2(value, time) {

    if(value) {
        nav.message = 'resetViewPosition';
    }

}


function button_press_3(value, time) {

    if(value) {
        nav.message = 'increaseNavSpeed';
    }

}


function button_press_4(value, time) {

    if(value) {
        nav.message = 'nextNavMode';
    }

}


function joystick_move_x(value, time) {

    joystick_x_adjusted = -value;

}

function joystick_move_y(value, time) {

    js_y = -value;

    if (js_y > 0.05 || js_y < -0.05) {

        wand_rotation_noroll = iowand.wandrotation;
        wand_rotation_noroll.z = 0.0;

        translation_vector = wand_rotation_noroll.multVec(SFVec3f(0.0, 0.0, js_y));

        translation_x = translation_vector.x;
        translation_y = translation_vector.y;
        translation_z = translation_vector.z;

    } else {
        translation_x = 0.0;
        translation_y = 0.0;
        translation_z = 0.0;
    }
}


function wand_rotation_changed(value, time) {

    if (js_y > 0.05 || js_y < -0.05) {

        wand_rotation_noroll = iowand.wandrotation;
        wand_rotation_noroll.z = 0.0;

        translation_vector = wand_rotation_noroll.multVec(SFVec3f(0.0, 0.0, js_y));
        translation_x = translation_vector.x;
        translation_y = translation_vector.y;
        translation_z = translation_vector.z;

    } else {
        translation_x = 0.0;
        translation_y = 0.0;
        translation_z = 0.0;
    }
}
