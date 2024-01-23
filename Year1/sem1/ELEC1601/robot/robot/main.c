#include "stdio.h"
#include "stdlib.h"
 #include "SDL2/SDL.h" 
#include "SDL2/SDL2_gfxPrimitives.h"
#include "time.h"

#include "formulas.h"
#include "wall.h"
#include "robot.h"

int done = 0;


int main(int argc, char *argv[]) {
    SDL_Window *window;
    SDL_Renderer *renderer;

    if(SDL_Init(SDL_INIT_VIDEO) < 0){
        return 1;
    }

    window = SDL_CreateWindow("Robot Maze", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, OVERALL_WINDOW_WIDTH, OVERALL_WINDOW_HEIGHT, SDL_WINDOW_OPENGL);
    window = SDL_CreateWindow("Robot Maze", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, OVERALL_WINDOW_WIDTH, OVERALL_WINDOW_HEIGHT, SDL_WINDOW_OPENGL);
    renderer = SDL_CreateRenderer(window, -1, 0);

    struct Robot robot;
    struct Wall_collection *head = NULL;
    int front_centre_sensor, left_sensor, right_sensor=0;
    clock_t start_time, end_time;
    int msec;
    int crashed = 0;

    // SETUP MAZE
    // You can create your own maze here. line of code is adding a wall.
    // You describe position of top left corner of wall (x, y), then width and height going down/to right
    // Relative positions are used (OVERALL_WINDOW_WIDTH and OVERALL_WINDOW_HEIGHT)
    // But you can use absolute positions. 10 is used as the width, but you can change this.
   
    insertAndSetFirstWall(&head, 1,  OVERALL_WINDOW_WIDTH/2, OVERALL_WINDOW_HEIGHT/2, 10, OVERALL_WINDOW_HEIGHT/2);
    insertAndSetFirstWall(&head, 2,  OVERALL_WINDOW_WIDTH/2-100, OVERALL_WINDOW_HEIGHT/2+100, 10, OVERALL_WINDOW_HEIGHT/2-100);
    insertAndSetFirstWall(&head, 3,  OVERALL_WINDOW_WIDTH/2-250, OVERALL_WINDOW_HEIGHT/2+100, 150, 10);
    insertAndSetFirstWall(&head, 4,  OVERALL_WINDOW_WIDTH/2-150, OVERALL_WINDOW_HEIGHT/2, 150, 10);
    insertAndSetFirstWall(&head, 5,  OVERALL_WINDOW_WIDTH/2-250, OVERALL_WINDOW_HEIGHT/2-200, 10, 300);
    insertAndSetFirstWall(&head, 6,  OVERALL_WINDOW_WIDTH/2-150, OVERALL_WINDOW_HEIGHT/2-100, 10, 100);
    insertAndSetFirstWall(&head, 7,  OVERALL_WINDOW_WIDTH/2-250, OVERALL_WINDOW_HEIGHT/2-200, 450, 10);
    insertAndSetFirstWall(&head, 8,  OVERALL_WINDOW_WIDTH/2-150, OVERALL_WINDOW_HEIGHT/2-100, 250, 10);
    insertAndSetFirstWall(&head, 9,  OVERALL_WINDOW_WIDTH/2+200, OVERALL_WINDOW_HEIGHT/2-200, 10, 300);
    insertAndSetFirstWall(&head, 10,  OVERALL_WINDOW_WIDTH/2+100, OVERALL_WINDOW_HEIGHT/2-100, 10, 300);
    insertAndSetFirstWall(&head, 11,  OVERALL_WINDOW_WIDTH/2+100, OVERALL_WINDOW_HEIGHT/2+200, OVERALL_WINDOW_WIDTH/2-100, 10);
    insertAndSetFirstWall(&head, 12,  OVERALL_WINDOW_WIDTH/2+200, OVERALL_WINDOW_HEIGHT/2+100, OVERALL_WINDOW_WIDTH/2-100, 10);

// insertAndSetFirstWall(&head, 1,  0, 0, 5, 100);
//     insertAndSetFirstWall(&head, 2,  100, 0, 5, 100);

//     insertAndSetFirstWall(&head, 3,  299.5, 100, 5, 80);
//     insertAndSetFirstWall(&head, 4,  399.5, 100, 5, 130);

//     insertAndSetFirstWall(&head, 5,  260, 370, 280, 5);
//     insertAndSetFirstWall(&head, 6,  5, OVERALL_WINDOW_HEIGHT-5, OVERALL_WINDOW_WIDTH, 5);

//     insertAndSetFirstWall(&head, 7,  OVERALL_WINDOW_WIDTH-105, 275, 5, 100);
//     insertAndSetFirstWall(&head, 8,  OVERALL_WINDOW_WIDTH-5, 175, 5, 300);

//     insertAndSetFirstWall(&head, 9,  440, 275, 100, 5);
//     insertAndSetFirstWall(&head, 10,  540, 175, 100, 5);

//     insertAndSetFirstWall(&head, 11,  OVERALL_WINDOW_WIDTH-200, 0, 5, 280);
//     insertAndSetFirstWall(&head, 12,  OVERALL_WINDOW_WIDTH-100, 100, 5, 80);

//     insertAndSetFirstWall(&head, 13,  OVERALL_WINDOW_WIDTH-100, 100, 100, 5);
//     insertAndSetFirstWall(&head, 14,  OVERALL_WINDOW_WIDTH-200, 0, 200, 5);




//     // Large Curved Wall
//     int radius = 150;
//     int centerX = 150;
//     int centerY = 100;
//     int numSegments = 1000;
//     double angleIncrement = M_PI / numSegments;

//     for (int i = 0; i < numSegments; i++) {
//         int startX = centerX - radius * cos(i * angleIncrement);
//         int startY = centerY + radius * sin(i * angleIncrement);
//         int endX = centerX - radius * cos((i + 1) * angleIncrement);
//         int endY = centerY + radius * sin((i + 1) * angleIncrement);

//         insertAndSetFirstWall(&head, i + 14, startX, startY, 5, 5);
//     }
//     // Small Curved Wall

//     for (int i = 0; i < numSegments; i++) {
//         int startX = 150 - 50 * cos(i * angleIncrement);
//         int startY = 100 + 50 * sin(i * angleIncrement);
//         int endX = 150 - 50 * cos((i + 1) * angleIncrement);
//         int endY = 100 + 50 * sin((i + 1) * angleIncrement);

//         insertAndSetFirstWall(&head, i + 14, startX, startY, 5, 5);
//     }
//     // Large Curved Wall2

//     for (int i = 0; i < numSegments; i++) {
//         int startX = 100 - 100 * sin(i * angleIncrement);
//         int startY = 300 + 100 * cos(i * angleIncrement);
//         int endX = 100 - 100 * cos((i + 1) * angleIncrement);
//         int endY = 300 + 100 * sin((i + 1) * angleIncrement);

//         insertAndSetFirstWall(&head, i + 14, startY, startX, 5, 5);
//     }
//     //diagonal left

//     for (int i = 0; i < numSegments; i++) {
//         int startX = 130 - 170 * cos(i * angleIncrement);
//         int startY = 350 + 170 * cos(i * angleIncrement);
//         int endX = 130 - 170 * cos((i + 1) * angleIncrement);
//         int endY = 350 + 170 * sin((i + 1) * angleIncrement);

//         insertAndSetFirstWall(&head, i + 14, startX, startY, 5, 5);

//         int startX2 = 330 - 70 * cos(i * angleIncrement);
//         int startY2 = 300 + 70 * cos(i * angleIncrement);
//         int endX2 = 330 - 70 * cos((i + 1) * angleIncrement);
//         int endY2 = 300 + 70 * sin((i + 1) * angleIncrement);

//         insertAndSetFirstWall(&head, i + 14, startX2, startY2, 5, 5);
//     }
    setup_robot(&robot);
    updateAllWalls(head, renderer);

    SDL_Event event;
    while(!done){
        SDL_SetRenderDrawColor(renderer, 200, 200, 200, 255);
        SDL_RenderClear(renderer);

        //Move robot based on user input commands/auto commands
        if (robot.auto_mode == 1)
            robotAutoMotorMove(&robot, front_centre_sensor, left_sensor, right_sensor);
        robotMotorMove(&robot, crashed);

        //Check if robot reaches endpoint. and check sensor values
        if (checkRobotReachedEnd(&robot, OVERALL_WINDOW_WIDTH, OVERALL_WINDOW_HEIGHT/2+100, 10, 100)){
            end_time = clock();
            msec = (end_time-start_time) * 1000 / CLOCKS_PER_SEC;
            robotSuccess(&robot, msec);
        }
        else if(crashed == 1 || checkRobotHitWalls(&robot, head)){
            robotCrash(&robot);
            crashed = 1;
        }
        //Otherwise compute sensor information
        else {
            front_centre_sensor = checkRobotSensorFrontCentreAllWalls(&robot, head);
            if (front_centre_sensor>0)
                printf("Getting close on the centre. Score = %d\n", front_centre_sensor);

            left_sensor = checkRobotSensorLeftAllWalls(&robot, head);
            if (left_sensor>0)
                printf("Getting close on the left. Score = %d\n", left_sensor);

            right_sensor = checkRobotSensorRightAllWalls(&robot, head);
            if (right_sensor>0)
                printf("Getting close on the right. Score = %d\n", right_sensor);
        }
        robotUpdate(renderer, &robot);
        updateAllWalls(head, renderer);

        // Check for user input
        SDL_RenderPresent(renderer);
        while(SDL_PollEvent(&event)){
            if(event.type == SDL_QUIT){
                done = 1;
            }
            const Uint8 *state = SDL_GetKeyboardState(NULL);
            if(state[SDL_SCANCODE_UP] && robot.direction != DOWN){
                robot.direction = UP;
            }
            if(state[SDL_SCANCODE_DOWN] && robot.direction != UP){
                robot.direction = DOWN;
            }
            if(state[SDL_SCANCODE_LEFT] && robot.direction != RIGHT){
                robot.direction = LEFT;
            }
            if(state[SDL_SCANCODE_RIGHT] && robot.direction != LEFT){
                robot.direction = RIGHT;
            }
            if(state[SDL_SCANCODE_SPACE]){
                setup_robot(&robot);
            }
            if(state[SDL_SCANCODE_RETURN]){
                robot.auto_mode = 1;
                start_time = clock();
            }
        }

        SDL_Delay(120);
    }
    SDL_DestroyRenderer(renderer);
    SDL_DestroyWindow(window);
    printf("DEAD\n");
}
