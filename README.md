

   ### Предметная область
    
    Есть несколько **Банков**, которые предоставляют финансовые услуги по операциям с деньгами.
    
    В банке есть **Счета** и **Клиенты**. У клиента есть имя, фамилия, адрес и номер паспорта (имя и фамилия обязательны, остальное – опционально).
    
    ### Счета и проценты
    
    Счета бывают трёх видов: **Дебетовый счет**, **Депозит** и **Кредитный счет**. Каждый счет принадлежит какому-то клиенту.
    
    > **Дебетовый счет** – обычный счет с фиксированным процентом на остаток. Деньги можно снимать в любой момент, в минус уходить нельзя. Комиссий нет.
    > 
    
    > **Депозитный счет** – счет, с которого нельзя снимать и переводить деньги до тех пор, пока не закончится его срок (пополнять можно). Процент на остаток зависит от изначальной суммы, **например**, если открываем депозит до 50 000 р. - 3%, если от 50 000 р. до 100 000 р. - 3.5%, больше 100 000 р. - 4%. Комиссий нет. Проценты должны задаваться для каждого банка свои.
    > 
    
    > **Кредитный счет** – имеет кредитный лимит, в рамках которого можно уходить в минус (в плюс тоже можно). Процента на остаток нет. Есть фиксированная комиссия за использование, если клиент в минусе.
    > 
    
    ### Комиссии
    
    Периодически банки проводят операции по выплате процентов и вычету комиссии. Это значит, что **нужен механизм ускорения времени**, чтобы посмотреть, что будет через день/месяц/год и т.п.
    
    Процент на остаток начисляется ежедневно от текущей суммы в этот день, но выплачивается раз в месяц (и для дебетовой карты и для депозита). Например, 3.65% годовых. Значит в день: 3.65% / 365 дней = 0.01%. У клиента сегодня 100 000 р. на счету - запомнили, что у него уже 10 р. Завтра ему пришла ЗП и стало 200 000 р. За этот день ему добавили ещё 20 р. На следующий день он купил себе новый ПК и у него осталось 50 000 р. - добавили 5 р. Таким образом, к концу месяца складываем все, что запоминали. Допустим, вышло 300 р. - эта сумма добавляется к счету или депозиту в текущем месяце.
    
    Разные банки предлагают разные условия. В каждом банке известны величины процентов и комиссий.
    
    ### Центральный банк
    
    Регистрацией всех банков, а также взаимодействием между банками занимается центральный банк. Он должен управлять банками (предоставлять возможность создать банк) и предоставлять необходимый функционал, чтобы банки могли взаимодействовать с другими банками (например, можно реализовать переводы между банками через него). Он также занимается уведомлением других банков о том, что нужно начислять остаток или комиссию - для этого механизма не требуется создавать таймеры и завязываться на реальное время.
    
    ### Операции и транзакции
    
    Каждый счет должен предоставлять механизм **снятия**, **пополнения** и **перевода** денег (то есть счетам нужны некоторые идентификаторы).
    
    Еще обязательный механизм, который должны иметь банки - **отмена транзакций**. Если вдруг выяснится, что транзакция была совершена злоумышленником, то такая транзакция должна быть отменена. Отмена транзакции подразумевает возвращение банком суммы обратно. Транзакция не может быть повторно отменена.
    
    Счёта должны хранить в себе историю совершённых над ними транзакций.
    
    ### Создание клиента и счета
    
    Клиент должен создаваться по шагам. Сначала он указывает имя и фамилию (обязательно), затем адрес (можно пропустить и не указывать), затем паспортные данные (можно пропустить и не указывать).
    
    Если при создании счета у клиента не указаны адрес или номер паспорта, мы объявляем такой счет (любого типа) сомнительным, и запрещаем операции снятия и перевода выше определенной суммы (у каждого банка своё значение). Если в дальнейшем клиент указывает всю необходимую информацию о себе - счет перестает быть сомнительным и может использоваться без ограничений.
    
    ### Обновление условий счетов
    
    Для банков требуется реализовать методы изменений процентов и лимитов не перевод. Также требуется реализовать возможность пользователям подписываться на информацию о таких изменениях - банк должен предоставлять возможность клиенту подписаться на уведомления. Стоит продумать расширяемую систему, в которой могут появится разные способы получения нотификаций клиентом (да, да, это референс на тот самый сайт). Например, когда происходит изменение лимита для кредитных карт - все пользователи, которые подписались и имеют кредитные карты, должны получить уведомление.

