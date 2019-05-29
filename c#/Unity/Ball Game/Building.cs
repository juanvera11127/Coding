using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Building : MonoBehaviour {
    private float speed;
    private Rigidbody rb;
    private int rand;

	// Use this for initialization
	void Start () {
        rb = GetComponent<Rigidbody>();
        Reset();
	}
	
	// Update is called once per frame
	void Update () {
        speed = Player.speed / 3;
        rb.velocity = new Vector3(0, 0, -speed);
        if(rb.position.z < -15)
        {
            Reset();
        }
	}

    void Reset()
    {
        rand = Random.Range(-1, 2);
        while(rand == 0)
        {
            rand = Random.Range(-1, 2);
        }
        rb.position = new Vector3(rand * Random.Range(9, 20), Random.Range(-5, -15), Random.Range(40, 45));
        gameObject.transform.localScale = new Vector3(Random.Range(2,3), Random.Range(15,18), Random.Range(2,3));
    }
}
