package com.company;



public class Main  {

    public static void main (String [] args) throws Exception {

	// History begins from here

        System_Messages System_Messages = new System_Messages(); //System messages object
        SC_Handler SC_Handler = new SC_Handler(); //System command handler

        System.out.println(System_Messages.Welcome_Message);


        SC_Handler.inputCommand(System_Messages.Waiting_Command_Message);

    }


}


// 1. Пишем письмо генерируем новый хэш и шифруем данные вместе с новым хэшем. записываем все в файл
// 2. Отправляем зашифрованное письмо но перед этим берем старый хэш - искажаем его нулями