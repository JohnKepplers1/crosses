package ru.ifmo.kravchenko;

public class Controller implements ControllerInterface {
    ModelInterface model;
    Window view;
    public Controller(ModelInterface model){
        this.model = model;


    }
    public void start(){
        System.out.println("Игра завершена");
    }
    public void stop(){

    }
}
