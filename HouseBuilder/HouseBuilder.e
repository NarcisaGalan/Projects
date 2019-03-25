;
; Copyright (c) 2005 IBM Corporation and others.
; All rights reserved. This program and the accompanying materials
; are made available under the terms of the Common Public License v1.0
; which accompanies this distribution, and is available at
; http://www.eclipse.org/legal/cpl-v10.html
;
; Contributors:
; IBM - Initial implementation
;
load foundations/Root.e
load foundations/EC.e



sort agent


;Decralam toate event-urile
event BuildFoundation(agent)
event BuildFloor(agent)
event BuildStructure(agent)
event BuildWalls(agent)
event BuildRoof(agent)
event InstallWindows(agent)
event IsolateHouse(agent)
event PaintHouse(agent)
event BuildInterior(agent)


;Declaram toti fluentii
fluent Wall(agent)
fluent Foundation(agent)
fluent Windows(agent)
fluent Structure(agent)
fluent Isolation(agent)
fluent Paint(agent)
fluent Interior(agent)
fluent Floor(agent)
fluent Roof(agent)


fluent AtWork(agent)
fluent OnVacantion(agent)

mutex BuildFoundation, BuildFloor,BuildStructure,BuildWalls, BuildRoof,InstallWindows,IsolateHouse,PaintHouse
noninertial AtWork, OnVacantion

;Legam toate event-urile cu fluentii lor corespunzatori
[agent,time] Initiates(BuildFoundation(agent),Foundation(agent),time).
[agent,time] Initiates(BuildFloor(agent),Floor(agent),time).
[agent,time] Initiates(BuildStructure(agent),Structure(agent),time).
[agent,time] Initiates(BuildWalls(agent),Wall(agent),time).
[agent,time] Initiates(BuildRoof(agent),Roof(agent),time).
[agent,time] Initiates(InstallWindows(agent),Windows(agent),time).
[agent,time] Initiates(IsolateHouse(agent),Isolation(agent),time).
[agent,time] Initiates(PaintHouse(agent),Paint(agent),time).
[agent,time] Initiates(BuildInterior(agent),Interior(agent),time).



; Punem ca preconditii ca inainte de fiecare componenta pe punctul de a se construi, sa fie
; construite componenetele precursoare 
; (fie chiar de agentul care construieste acum, fie de un alt anget)

[agent,time]
HoldsAt(Structure(agent),time) ->
 ({agent1} agent1!=agent  & (HoldsAt(Foundation(agent1),time-1))) |
 HoldsAt(Foundation(agent),time-1) .

[agent,time]
HoldsAt(Wall(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Structure(agent1),time-1))) | 
 HoldsAt(Structure(agent),time-1).

[agent,time]
HoldsAt(Floor(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Wall(agent1),time-1))) |
 HoldsAt(Wall(agent),time-1).

[agent,time]
HoldsAt(Roof(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Wall(agent1),time-1))) |
 HoldsAt(Wall(agent),time-1).

[agent,time]
HoldsAt(Windows(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Roof(agent1),time-1))) |
 HoldsAt(Roof(agent),time-1).

[agent,time]
HoldsAt(Isolation(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Windows(agent1),time-1))) |
 HoldsAt(Windows(agent),time-1).

[agent,time]
HoldsAt(Paint(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Isolation(agent1),time-1))) |
 HoldsAt(Isolation(agent),time-1).

[agent,time]
HoldsAt(Interior(agent),time) ->
({agent1} agent1!=agent  & (HoldsAt(Isolation(agent1),time-1))) |
 HoldsAt(Isolation(agent),time-1).


[agent,time] 
Happens(IsolateHouse(agent),time) -> !Happens(InstallWindows(agent),time) & !Happens(PaintHouse(agent),time) & !Happens(BuildRoof(agent),time) & !Happens(BuildFloor(agent),time) & !Happens(BuildInterior(agent),time).
[agent,time] 
Happens(InstallWindows(agent),time) -> !Happens(IsolateHouse(agent),time) & !Happens(PaintHouse(agent),time) & !Happens(BuildRoof(agent),time) & !Happens(BuildFloor(agent),time) & !Happens(BuildInterior(agent),time).
[agent,time] 
Happens(PaintHouse(agent),time) -> !Happens(IsolateHouse(agent),time) & !Happens(InstallWindows(agent),time) & !Happens(BuildRoof(agent),time) & !Happens(BuildFloor(agent),time) & !Happens(BuildInterior(agent),time).
[agent,time] 
Happens(BuildRoof(agent),time) -> !Happens(IsolateHouse(agent),time) & !Happens(PaintHouse(agent),time) & !Happens(InstallWindows(agent),time) & !Happens(BuildFloor(agent),time) & !Happens(BuildInterior(agent),time).
[agent,time] 
Happens(BuildFloor(agent),time) -> !Happens(IsolateHouse(agent),time) & !Happens(PaintHouse(agent),time) & !Happens(BuildRoof(agent),time) & !Happens(InstallWindows(agent),time) & !Happens(BuildInterior(agent),time).
[agent,time] 
Happens(BuildInterior(agent),time) -> !Happens(IsolateHouse(agent),time) & !Happens(PaintHouse(agent),time) & !Happens(BuildRoof(agent),time) & !Happens(BuildFloor(agent),time) & !Happens(InstallWindows(agent),time).


[agent,time]
Happens(BuildStructure(agent), time) -> !({agent1} agent1 != agent & Happens(BuildStructure(agent1), time)).
[agent,time]
Happens(BuildInterior(agent),time) -> !({agent1} agent1 != agent & Happens(BuildInterior(agent1), time)).
[agent,time]
Happens(PaintHouse(agent),time) -> !({agent1} agent1 != agent & Happens(InstallWindows(agent1), time)).
[agent,time]
Happens(InstallWindows(agent),time) -> !({agent1} agent1 != agent & Happens(InstallWindows(agent1), time)).
[agent,time]
Happens(BuildRoof(agent),time) -> !({agent1} agent1 != agent & Happens(BuildRoof(agent1), time)).
[agent,time]
Happens(BuildWalls(agent),time) -> !({agent1} agent1 != agent & Happens(BuildWalls(agent1), time)).
[agent,time]
Happens(BuildFoundation(agent),time) -> !({agent1} agent1 != agent & Happens(BuildFoundation(agent1), time)).
[agent,time]
Happens(BuildFloor(agent),time)->!({agent1} agent1 != agent & Happens(BuildFloor(agent1), time)).


; Interzicem ca dupa finalizarea executiei unei componente, aceasta sa fie efectuata iar
[agent, time]
HoldsAt(Foundation(agent),time) ->
!Happens(BuildFoundation(agent),time) &
!({agent1} agent1!=agent & Happens(BuildFoundation(agent1),time)).

[agent, time]
HoldsAt(Structure(agent),time) ->
!Happens(BuildStructure(agent),time)&
!({agent1} agent1!=agent & Happens(BuildStructure(agent1),time)).

[agent, time]
HoldsAt(Wall(agent),time) ->
!Happens(BuildWalls(agent),time)&
!({agent1} agent1!=agent & Happens(BuildWalls(agent1),time)).

[agent, time]
HoldsAt(Roof(agent),time) ->
!Happens(BuildRoof(agent),time)&
!({agent1} agent1!=agent & Happens(BuildRoof(agent1),time)).

[agent, time]
HoldsAt(Windows(agent),time) ->
!Happens(InstallWindows(agent),time)&
!({agent1} agent1!=agent & Happens(InstallWindows(agent1),time)).

[agent, time]
HoldsAt(Isolation(agent),time) ->
!Happens(IsolateHouse(agent),time)&
!({agent1} agent1!=agent & Happens(IsolateHouse(agent1),time)).

[agent, time]
HoldsAt(Paint(agent),time) ->
!Happens(PaintHouse(agent),time)&
!({agent1} agent1!=agent & Happens(PaintHouse(agent1),time)).

[agent, time]
HoldsAt(Interior(agent),time) ->
!Happens(BuildInterior(agent),time)&
!({agent1} agent1!=agent & Happens(BuildInterior(agent1),time)).

[agent, time]
HoldsAt(Floor(agent),time) ->
!Happens(BuildInterior(agent),time)&
!({agent1} agent1!=agent & Happens(BuildInterior(agent1),time)).


; Daca agentul este in vacanta acesta nu este la munca si
; e obligat sa vina la munca pentru urmatoarea executie
[agent,time]
HoldsAt(OnVacantion(agent),time)->
!HoldsAt(AtWork(agent),time) & !HoldsAt(OnVacantion(agent),time+1) & HoldsAt(AtWork(agent),time+1).

;Daca un om nu e in vacanta atunci e la munca
[agent,time]
!HoldsAt(OnVacantion(agent),time)-> HoldsAt(AtWork(agent),time).

; Daca un agent este in vacanta acesta nu poate construi nimic
[agent,time]
!HoldsAt(AtWork(agent),time)->
!Happens(BuildInterior(agent),time) & !Happens(PaintHouse(agent),time) & !Happens(InstallWindows(agent),time) & !Happens(BuildRoof(agent),time) & !Happens(BuildWalls(agent),time) & !Happens(BuildStructure(agent),time) & !Happens(BuildFoundation(agent),time) & !Happens(BuildFloor(agent),time).



; STABILIREA SCENARIULUI

agent Vasile, Ion

!(HoldsAt(Roof(Vasile),0) | HoldsAt(Roof(Ion),0)).
!(HoldsAt(Floor(Vasile),0) | HoldsAt(Floor(Ion),0)).
!(HoldsAt(Interior(Vasile),0) | HoldsAt(Interior(Ion),0)).
!(HoldsAt(Isolation(Vasile),0) | HoldsAt(Isolation(Ion),0)).
!(HoldsAt(Wall(Vasile),0) | HoldsAt(Wall(Ion),0)).
!(HoldsAt(Structure(Vasile),0) | HoldsAt(Structure(Ion),0)).
!(HoldsAt(Windows(Vasile),0) | HoldsAt(Windows(Ion),0)).
!(HoldsAt(Paint(Vasile),0) | HoldsAt(Paint(Ion),0)).
!(HoldsAt(Foundation(Vasile),0) | HoldsAt(Foundation(Ion),0)).

HoldsAt(AtWork(Ion),0).
HoldsAt(OnVacantion(Vasile),0).



HoldsAt(OnVacantion(Vasile),4).



HoldsAt(Roof(Vasile),9) | HoldsAt(Roof(Ion),9).
HoldsAt(Floor(Vasile),9) | HoldsAt(Floor(Ion),9).
HoldsAt(Interior(Vasile),9) | HoldsAt(Interior(Ion),9).
HoldsAt(Isolation(Vasile),9) | HoldsAt(Isolation(Ion),9).
HoldsAt(Wall(Vasile),9) | HoldsAt(Wall(Ion),9).
HoldsAt(Structure(Vasile),9) | HoldsAt(Structure(Ion),9).
HoldsAt(Windows(Vasile),9) | HoldsAt(Windows(Ion),9).
HoldsAt(Paint(Vasile),9) | HoldsAt(Paint(Ion),9).

range time 0 9
range offset 1 1
