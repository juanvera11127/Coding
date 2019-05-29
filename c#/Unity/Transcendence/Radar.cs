using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Radar : MonoBehaviour {
    public int radius;
    public int maxRadius;
    public int ticks;
    public int ticksPerInc;
	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {
        ticks++;
        if(ticks%ticksPerInc == 0)
        {
            radius++;
            Collider2D[] objects = Physics2D.OverlapCircleAll(transform.position, radius + 1);
            foreach (Collider2D c in objects)
            {
                //Skip already-pinged objects
                if ((c.transform.position - transform.position).magnitude < radius)
                {
                    continue;
                }
                Ping ping = c.gameObject.GetComponent<Ping>();
                if (ping)
                {
                    ping.activate();
                }
            }
        }

        if(radius > maxRadius)
        {
            Destroy(gameObject);
        }
	}
}
